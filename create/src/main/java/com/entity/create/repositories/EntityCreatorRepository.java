package com.entity.create.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.create.entities.EntityCreatorTestingEntity;


@Repository
public interface EntityCreatorRepository extends JpaRepository<EntityCreatorTestingEntity, Long> {

	@Query(value = "select table_name,column_name,data_type from information_schema.\"columns\" where table_name = :tableName \r\n"
			+ "order by ordinal_position",nativeQuery = true)
	List<Object[]> getTableData(String tableName);
	
	@Query(value = "select \r\n"
			+ "       cast(kcu.column_name as character varying) as key_column\r\n"
			+ "from information_schema.table_constraints tco\r\n"
			+ "join information_schema.key_column_usage kcu \r\n"
			+ "     on kcu.constraint_name = tco.constraint_name\r\n"
			+ "     and kcu.constraint_schema = tco.constraint_schema\r\n"
			+ "     and kcu.constraint_name = tco.constraint_name\r\n"
			+ "where tco.constraint_type = 'PRIMARY KEY' and kcu.table_name = :tableName\r\n"
			+ "order by kcu.table_schema,\r\n"
			+ "         kcu.table_name",nativeQuery = true)
	String getPrimaryKeyColumnName(String tableName);
}
