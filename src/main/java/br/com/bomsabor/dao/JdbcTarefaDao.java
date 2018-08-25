package br.com.bomsabor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bomsabor.modelo.Tarefa;

/**
 * Para o Spring conseguir criar o TarefaDao vamos declarar a classe como
 * componente. Aqui o Spring possui a anota��o @Repository que deve ser
 * utilizada nas classes como o DAO. Al�m disso, vamos tamb�m amarrar a conex�o
 * com @Autowired
 * 
 */
@Repository
public class JdbcTarefaDao {

	// a conex�o com o banco de dados
	private Connection connection;

	public JdbcTarefaDao(Connection connection) {
		this.connection = connection;
	}

	public JdbcTarefaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	/**
	 * Ao usar @Autowired no construtor, o Spring tenta descobrir como abrir uma
	 * conex�o,mas claro que o Container n�o faz ideia com qual banco queremos nos
	 * conectar. Para solucionar isso o Spring oferece uma configura��o de XML que
	 * define um provedor de conex�es. No mundo JavaEE, este provedor � chamado
	 * DataSource e abstrai as configura��es de Driver, URL, etc da aplica��o.
	 * 
	 * Ver arquivo spring-context.xml
	 * 
	 */
	@Autowired
	public JdbcTarefaDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Tarefa tarefa) {
		String sql = "insert into tarefa " + "(descricao,finalizado,dataFinalizacao)" + " values (?,?,?)";
		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, tarefa.getDescricao());
			tarefa.setFinalizado(false);
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tarefa> getLista() {
		try {
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			PreparedStatement stmt = this.connection.prepareStatement("select *	from tarefa");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato

				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("idtarefa"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				if (rs.getDate("datafinalizacao") != null) {
					// montando a data atrav�s do Calendar
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("datafinalizacao"));
					tarefa.setDataFinalizacao(data);
				}
				// adicionando o objeto � lista
				tarefas.add(tarefa);
			}
			rs.close();
			stmt.close();
			return tarefas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Tarefa tarefa) {
		String sql = "update tarefa set descricao=?, finalizado=?, datafinalizacao=? where idtarefa=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			tarefa.setFinalizado(false);
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(4, tarefa.getId());
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Tarefa tarefa) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " + "from tarefa where idtarefa=?");
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Tarefa buscaPorId(Long id) {
		Tarefa tarefa = new Tarefa();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from tarefa where idtarefa =?");
			stmt.setLong(1, id); // o id � o argumento recebido pelo m�todo
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tarefa.setId(rs.getLong("idtarefa"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				// montando a data atrav�s do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("datafinalizacao"));
				tarefa.setDataFinalizacao(data);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return tarefa;
	}

	public void finaliza(Tarefa tarefa) {
		String sql = "update tarefa set finalizado=? where idtarefa=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setLong(2, tarefa.getId());
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
