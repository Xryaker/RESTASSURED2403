package config;

import org.junit.BeforeClass;
import specification.RickAndMortySpecification;

public class BaseClass {
    static final String URL="https://rickandmortyapi.com/api/";
    @BeforeClass
    public static void b(){
        RickAndMortySpecification.createEMcpecification(URL);
    }
}
