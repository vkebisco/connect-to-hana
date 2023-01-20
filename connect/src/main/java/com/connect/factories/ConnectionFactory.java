package com.connect.factories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionFactory {

    private static final Connection conn;

    /**
     * A conexão é criada na primeira vez que o método getConnection() é chamado
     */
    static {
        try {
            //pega senha, username e URL das variáveis de ambiente
            var envVariables = getEnv();

            //Faz a conexão com o HANA Cloud

            //!!!IMPORTANTE
            // é boa prática colocar dados sensiveis em um arquivo .env do que escrever diretamente no código

            String senha = envVariables.get(0);
            String username = envVariables.get(1);
            String url = envVariables.get(2);

            conn = DriverManager.getConnection(url, username,senha);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lê as variaveis de ambiente do arquivo .env
     * @return
     * @throws IOException
     */
    private static List<String> getEnv() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(".env"));
        String line, variable;
        List<String> objects = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            variable = line.split("=")[1];
            objects.add(variable);

        }
        return objects;
    }

    public static Connection getConnection(){
        return conn;
    }

}
