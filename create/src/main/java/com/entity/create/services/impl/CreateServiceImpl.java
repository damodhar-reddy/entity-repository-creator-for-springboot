package com.entity.create.services.impl;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.create.repositories.EntityCreatorRepository;
import com.entity.create.requests.EntityCreateRequest;
import com.entity.create.services.CreateService;

@Service
public class CreateServiceImpl implements CreateService {
	@Autowired
	private EntityCreatorRepository entityCreatorRepository;

	public String entityCreator(EntityCreateRequest entityCreateRequest) {
		String tableName = entityCreateRequest.getTableName();
		List<Object[]> tableData = entityCreatorRepository.getTableData(tableName);
		if (tableData.size() > 0) {
			StringBuilder entityString = new StringBuilder();
			entityString.append("import javax.persistence.Column;\r\n" + "import javax.persistence.Entity;\r\n"
					+ "import javax.persistence.GeneratedValue;\r\n" + "import javax.persistence.GenerationType;\r\n"
					+ "import javax.persistence.Id;\r\n" + "import javax.persistence.Table;\r\n" + "\r\n"
					+ "import lombok.AllArgsConstructor;\r\n" + "import lombok.Data;\r\n"
					+ "import lombok.NoArgsConstructor;\r\n" + "import lombok.ToString;" + "\r\n" + "@Data\r\n"
					+ "@NoArgsConstructor\r\n" + "@AllArgsConstructor\r\n" + "@ToString\r\n" + "@Entity\r\n"
					+ "@Table(name = \"");
			entityString.append(tableName.toUpperCase() + "\")\r\n\r\n\r\n" + "public class "
					+ convetToClassName(tableName) + "Entity { \r\n\r\n");
			String primaryKeyColumn = entityCreatorRepository.getPrimaryKeyColumnName(tableName);
			// column names
			tableData.forEach(data -> {
				String columnName = data[1].toString();
				System.out.println(primaryKeyColumn + " " + primaryKeyColumn.getClass() + " "
						+ primaryKeyColumn.length() + " " + primaryKeyColumn.equals(data[2]));
				System.out.println(columnName + " " + columnName.getClass() + " " + columnName.length() + " "
						+ columnName.equals(primaryKeyColumn));
				if (primaryKeyColumn.equals(columnName)) {
					entityString.append("	@Id\r\n" + "	@GeneratedValue(strategy = GenerationType.IDENTITY)\r\n");
				}
				entityString.append("	@Column(name = \"");
				entityString.append(data[1].toString().toUpperCase() + "\")\r\n");
				String dataType = "";
				switch (data[2].toString()) {
				case "integer":
				case "bigint":
					dataType = "Long";
					break;
				case "double precision":
					dataType = "Double";
					break;
				case "boolean":
					dataType = "Boolean";
					break;
				case "character":
					dataType = "char";
					break;
				case "date":
				case "time with time zone":
				case "time without time zone":
				case "timestamp with time zone":
				case "timestamp without time zone":
					dataType = "Date";
					break;
				default:
					dataType = "String";
				}
				entityString
						.append("	private " + dataType + " " + convetToCamelCase(data[1].toString()) + ";\r\n\r\n");
			});
			entityString.append("	}");
			// to write into file

			try {
				FileWriter fileWriter = new FileWriter("D:\\PS-10\\" + tableName + "_entity.txt");
				PrintWriter printWriter = new PrintWriter(fileWriter);
				printWriter.print(entityString);
				printWriter.close();
				return "Entity Created Successfully in : D:\\PS-10\\" + tableName + "_entity.txt";
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "Exception : " + e.getLocalizedMessage();
			}
		} else {
			return "Table Data Not Found";
		}
	}

	@Override
	public String repositoryCreator(EntityCreateRequest repositoryCreateRequest) {
		String tableName = repositoryCreateRequest.getTableName();
		List<Object[]> tableData = entityCreatorRepository.getTableData(tableName);
		if (tableData.size() > 0) {
			StringBuilder repoString = new StringBuilder();
			repoString.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n"
					+ "import org.springframework.data.jpa.repository.Query;\r\n"
					+ "import org.springframework.stereotype.Repository;\r\n\r\n" + "@Repository\r\n"
					+ "public interface " + convetToClassName(tableName) + "Repository extends JpaRepository<"
					+ convetToClassName(tableName) + "Entity, Long> { \r\n\r\n	}");

			try {
				FileWriter fileWriter = new FileWriter("D:\\PS-10\\" + tableName + "_repository.txt");
				PrintWriter printWriter = new PrintWriter(fileWriter);
				printWriter.print(repoString);
				printWriter.close();
				return "Repository Created Successfully in : D:\\PS-10\\" + tableName + "_repository.txt";
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "Exception : " + e.getLocalizedMessage();
			}
		} else {
			return "Table Data Not Found";
		}
	}

	public String convetToCamelCase(String input) {
		StringBuilder result = new StringBuilder();

		// Split the input string by underscore
		String[] words = input.split("_");

		// Convert the first word to lowercase and append to the result
		result.append(words[0].toLowerCase());

		// Convert the remaining words to camel case and append to the result
		for (int i = 1; i < words.length; i++) {
			result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1).toLowerCase());
		}
		return result.toString();
	}

	public String convetToClassName(String input) {
		StringBuilder result = new StringBuilder();

		// Split the input string by underscore
		String[] words = input.split("_");

		// Convert the remaining words to camel case and append to the result
		for (int i = 0; i < words.length; i++) {
			result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1).toLowerCase());
		}
		return result.toString();
	}
}
