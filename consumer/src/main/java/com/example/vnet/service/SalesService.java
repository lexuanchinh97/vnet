package com.example.vnet.service;

import com.example.vnet.mapper.SalesDataMapper;
import com.example.vnet.model.AggregationType;
import com.example.vnet.model.SalesData;
import com.example.vnet.model.SalesDataDto;
import com.example.vnet.repository.SalesRepository;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesService {

    public static final String UNITS = "units";
    public static final String REVENUE = "revenue";
    public static final String PRODUCT_NAME = "productName";
    public static final String STORE_NAME = "storeName";
    private final SalesRepository salesRepository;
    private final EntityManager entityManager;

    public void save(SalesData salesData) {
        salesRepository.save(salesData);
    }

    public List<SalesDataDto> aggregateByProductAndStore(String search, AggregationType aggregationType) {
        CriteriaQuery<SalesData> query;
        if (search != null) {
            RSQLVisitor<CriteriaQuery<SalesData>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
            Node rootNode = new RSQLParser().parse(search);
            query = rootNode.accept(visitor, entityManager);
        } else {
            query = entityManager.getCriteriaBuilder().createQuery(SalesData.class);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<SalesData> root = query.from(SalesData.class);
        switch (Optional.ofNullable(aggregationType).orElse(AggregationType.ALL)) {
            case PRODUCT:
                query.multiselect(
                        root.get(PRODUCT_NAME),
                        criteriaBuilder.sum(root.get(UNITS)),
                        criteriaBuilder.sum(root.get(REVENUE))
                );
                query.groupBy(root.get(PRODUCT_NAME));
                break;
            case STORE:
                query.multiselect(
                        root.get(STORE_NAME),
                        criteriaBuilder.sum(root.get(UNITS)),
                        criteriaBuilder.sum(root.get(REVENUE))
                );
                query.groupBy(root.get(STORE_NAME));
                break;
            default:
                query.multiselect(
                        root.get(PRODUCT_NAME),
                        root.get(STORE_NAME),
                        criteriaBuilder.sum(root.get(UNITS)),
                        criteriaBuilder.sum(root.get(REVENUE))
                );
                query.groupBy(
                        root.get(PRODUCT_NAME),
                        root.get(STORE_NAME)
                );

                break;
        }
        List<SalesData> salesData = entityManager.createQuery(query).getResultList();
        return salesData.stream().map(SalesDataMapper.INSTANCE::toSaleData).collect(Collectors.toList());
    }
}
