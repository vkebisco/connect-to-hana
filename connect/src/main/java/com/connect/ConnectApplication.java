package com.connect;

import com.connect.dao.Queries;
import com.connect.domain.User;
import com.connect.utils.RandomString;

import java.sql.SQLException;
import java.util.List;

public class ConnectApplication {
	public static void main(String[] args) throws SQLException {

		//cria nova instância da classe Queries
		Queries queries = new Queries();

		//faz um select de todos os usuários

		List<User> users = null;
		try {
			users = queries.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// escreve o resultado no terminal
		System.out.println("SELECT");
		printResult(users);

		//pega o ID do ultimo User da lista
		int ultimoId = 0;
		if (!users.isEmpty()){
			ultimoId = users.get(users.size() -1).getId();
		}


		User userToInsert = null;

		//Cria um novo user com um nome aleatório
		if (ultimoId > 0){
			userToInsert  = new User(ultimoId + 1, RandomString.randomString());
		}else {
			userToInsert = new User(1, RandomString.randomString());
		}

		//insere o user no HANA
		try {
			queries.insert(userToInsert);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//mostra resultados
		System.out.println("INSERT");
		printResult(queries.getAllUsers());

		userToInsert.setName("nome nao aleatorio");

		try {
			queries.update(userToInsert);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("UPDATE");
		printResult(queries.getAllUsers());

		try {
			queries.delete(userToInsert.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		System.out.println("DELETE");
		printResult(queries.getAllUsers());

	}

	private static void printResult(List<User> users){
		System.out.println("\n");
		System.out.println("--- // ---");
		System.out.println("id " + "|"+  " nome");
		System.out.println("--" + " " + "--");

		users.forEach(user -> {
			System.out.println(user.getId() + " | " + user.getName());
		});

		System.out.println("\n");
	}
}