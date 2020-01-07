package com.wwy.test.service;

import com.wwy.entry.APIEntry;
import com.wwy.entry.Car;

public interface DataSourceTestService {

	APIEntry read();

	APIEntry write(Car car);

}
