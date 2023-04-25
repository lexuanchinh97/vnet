package com.example.vnet.mapper;

import com.example.vnet.config.MappingConfig;
import com.example.vnet.model.SalesData;
import com.example.vnet.model.SalesDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Execution Information Mapper.
 */
@Mapper(config = MappingConfig.class)
public interface SalesDataMapper {

    SalesDataMapper INSTANCE = Mappers.getMapper(SalesDataMapper.class);

    @Mapping(source = "units", target = "totalUnits")
    @Mapping(source = "revenue", target = "totalRevenue")
    SalesDataDto toSaleData(SalesData salesData);
}
