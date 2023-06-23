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

@Document(indexName = "sucursales", createIndex = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Sucursal {

	@Id
	private String id;
	
	@MultiField(mainField = @Field(type = FieldType.Keyword, name = "nombre"),
		      otherFields = @InnerField(suffix = "buscar", type = FieldType.Search_As_You_Type))
	private String nombre;
	
	@Field(type = FieldType.Text, name = "direccion")
	private String direccion;
	
	@Field(type = FieldType.Keyword, name = "telefono")
	private String telefono;
	
	@Field(type = FieldType.Keyword, name = "correo")
	private String correo;
}
