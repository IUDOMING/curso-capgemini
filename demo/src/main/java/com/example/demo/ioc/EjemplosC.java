package com.example.demo.ioc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

public class EjemplosC {

	@Autowired
	@Qualifier("Remote")
	private StringService srv;

	@Autowired
	@Qualifier("Local")
	private StringService srvLocal;

	// Introducimos el valor por defecto que se encuentra en application.properties,
	// usando el nombre que tiene en
	// dicho documento
	// Por lo general, la propia inyección te hace el casting si es posible
	// Sin valor: cuando no encuentra el valor
	@Value("${mi.valor:(Sin valor)}")
	private String config;

	// Instanciamos un JdbcTemplate que nos proporciona el propio Spring
	// Usamos @Autowired para instanciarla sin la necesidad del constructor
	@Autowired
	JdbcTemplate jdbcTemplate;

	// Construimos la clase/objetp Actor que está relacionada con la tabla en la
	// BBDD
	@Data
	@AllArgsConstructor
	class Actor {
		private int id;
		private String first_name, last_name;
	}

	// Creamos la clase que nos permitirá conseguir los datos de la bbdd.
	// RowMapper es una clase de Java
	class ActorRowMapper implements RowMapper<Actor> {
		@Override
		public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Actor(rs.getInt("actor_id"), rs.getString("last_name"), rs.getString("first_name"));
		}
	}

	@Autowired
	private Rango rango;

	/*
	 * @Autowired //Busca donde está la anotación @Components o derivados y lo
	 * instancia. private StringServiceImpl srv;
	 */

	@Autowired
	UnaTonteria tonteria;


	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Aplicación arrancada");

		// Inyección manual
		/*
		 * StringRepository dao = new StringRepositoryImpl(); dao = new
		 * StringRepositoryImplMock(); var srv= new StringServiceImpl(dao);
		 */
		System.out.println(srv.get(1));
		System.out.println(srvLocal.get(1));
		System.out.println(rango.toString());
		System.out.println(rango.getMax() + rango.getMin());
		// System.out.println(tonteria.dimeAlgo());
		System.out.println(tonteria != null ? tonteria.dimeAlgo() : "Tonteria nula");
		System.out.println(config);

		var lst = jdbcTemplate.query("""
				SELECT actor_id, first_name, last_name
				from actor
				""", new ActorRowMapper());
		// lst.forEach(System.out::println);
		jdbcTemplate.queryForList("""
				SELECT concat(first_name, ' ', last_name)
				from actor
				""", String.class).forEach(System.out::println);

	}

}
