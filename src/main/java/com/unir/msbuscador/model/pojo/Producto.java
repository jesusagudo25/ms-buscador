package com.unir.msbuscador.model.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(indexName = "productos", createIndex = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Producto {

	@Id
	private String id;
	
	@MultiField(mainField = @Field(type = FieldType.Keyword, name = "nombre"),
		      otherFields = @InnerField(suffix = "buscar", type = FieldType.Search_As_You_Type))
	private String nombre;
	
	@Field(type = FieldType.Keyword, name = "codigo")
	private String codigo;
	
	@Field(type = FieldType.Double, name = "precio")
	private Double precio;
	
	@Field(type = FieldType.Integer, name = "cantidad")
	private int cantidad;
}
