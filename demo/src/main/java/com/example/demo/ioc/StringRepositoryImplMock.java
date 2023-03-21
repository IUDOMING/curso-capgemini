package com.example.demo.ioc;

import org.springframework.stereotype.Repository;

@Repository
public class StringRepositoryImplMock implements StringRepository {

	@Override
	public String load() {
		// TODO Auto-generated method stub
		return "Soy el doble de StringRepository y Mock";
	}

	@Override
	public void save(String item) {
		System.out.println("No guardo el item: " + item);

	}

}