package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.Bill;
import org.springframework.data.repository.CrudRepository;

public interface IBillRepository extends CrudRepository<Bill, Integer>
{
}
