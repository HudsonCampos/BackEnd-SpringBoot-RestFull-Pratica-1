package com.curso.poo.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parseListObject(List<O> origins, Class<D> destination) {
		List<D> parseListObjetct = new ArrayList<>();
		for(Object o: origins) {
			parseListObjetct.add(mapper.map(o, destination));
		}
		
		return parseListObjetct;
	}
}
