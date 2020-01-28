package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.VisitLog;
import org.springframework.data.repository.CrudRepository;

public interface IVisitLogRepository extends CrudRepository<VisitLog, Integer>
{
}
