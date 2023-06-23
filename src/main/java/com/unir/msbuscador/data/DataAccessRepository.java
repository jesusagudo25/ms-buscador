package com.unir.msbuscador.data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import com.unir.msbuscador.model.pojo.Producto;
import com.unir.msbuscador.model.pojo.ProductoProveedor;
import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.pojo.Sucursal;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DataAccessRepository {

	private final ProductoProveedorRepository productoProveedorRepository;
	private final ProductoRepository productoRepository;
	private final ProveedorRepository proveedorRepository;
	private final SucursalRepository sucursalRepository;
	private final ElasticsearchOperations elasticClient;
	
	private final String[] nombreSearchFields = { "nombre.buscar", "nombre.buscar._2gram", "nombre.buscar._3gram" };
	
	// Metodos para trabajar con Productos con Spring Data Elasticsearch - JPA
	public List<Producto> getProductos() {
		return productoRepository.findAll();
	}
	
	public List<Producto> findByNombreContainingProducto(String nombre){
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		
		boolQuery.must(QueryBuilders.multiMatchQuery(nombre, nombreSearchFields)
				.type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));
		
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQuery);
		Query query = nativeSearchQueryBuilder.build();
		SearchHits<Producto> result = elasticClient.search(query, Producto.class);
		return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
	}
	
	public Producto findProductoByCodigo(String codigo) {
		return productoRepository.findByCodigo(codigo);
	}
	
	public void deleteProducto(Producto producto) {
		productoRepository.delete(producto);
	}

	public Producto saveOrUpdateProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
	// Metodos para trabajar con Sucursales con Spring Data Elasticsearch - JPA
	public List<Sucursal> getSucursales() {
		return sucursalRepository.findAll();
	}

	public Optional<Sucursal> findSucursalById(String id) {
		return sucursalRepository.findById(id);
	}
	
	public List<Sucursal> findByNombreContainingSucursal(String nombre) {

		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		
		boolQuery.must(QueryBuilders.multiMatchQuery(nombre, nombreSearchFields)
				.type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));
		
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQuery);
		Query query = nativeSearchQueryBuilder.build();
		SearchHits<Sucursal> result = elasticClient.search(query, Sucursal.class);
		return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
	}
	
	public void deleteSucursal(Sucursal sucursal) {
		sucursalRepository.delete(sucursal);
	}

	public Sucursal saveOrUpdateSucursal(Sucursal sucursal) {
		return sucursalRepository.save(sucursal);
	}
	
	// Metodos para trabajar con Proveedor con Spring Data Elasticsearch - JPA
	public List<Proveedor> getProveedores() {
		return proveedorRepository.findAll();
	}
	
	public List<Proveedor> findByNombreContainingProveedor(String nombre) {
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		
		boolQuery.must(QueryBuilders.multiMatchQuery(nombre, nombreSearchFields)
				.type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));
		
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQuery);
		Query query = nativeSearchQueryBuilder.build();
		SearchHits<Proveedor> result = elasticClient.search(query, Proveedor.class);
		return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
	}
	
	public Boolean existsProveedorById(String id) {
		return proveedorRepository.existsById(id);
	}

	public Optional<Proveedor> findProveedorById(String id) {
		return proveedorRepository.findById(id);
	}

	public void deleteProveedor(Proveedor proveedor) {
		proveedorRepository.delete(proveedor);
	}
	
	public Proveedor saveOrUpdateProveedor(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}
	
	// Metodos para trabajar con ProductosProveedores con Spring Data Elasticsearch - JPA
	public List<ProductoProveedor> findByNombreContainingAndProveedorId(String nombre, String proveedorId) {
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		
		boolQuery.must(QueryBuilders.termQuery("proveedorId", proveedorId));
		
		boolQuery.must(QueryBuilders.multiMatchQuery(nombre, nombreSearchFields)
				.type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));
		
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQuery);
		Query query = nativeSearchQueryBuilder.build();
		SearchHits<ProductoProveedor> result = elasticClient.search(query, ProductoProveedor.class);
		return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
	}

	public List<ProductoProveedor> findByProveedorId(String id) {
		return productoProveedorRepository.findByProveedorId(id);
	}
	
	public ProductoProveedor findProductoProveedorByCodigo(String codigo) {
		return productoProveedorRepository.findByCodigo(codigo);
	}
	
	public void deleteProductoProveedor(ProductoProveedor productoProveedor) {
		productoProveedorRepository.delete(productoProveedor);
	}

	public ProductoProveedor saveOrUpdateProductoProveedor(ProductoProveedor productoProveedor) {
		return productoProveedorRepository.save(productoProveedor);
	}
}
