public class SomeSingleton {

    @AlertIfNotStatic("Instance attribute should be static") 
    private SomeSingleton instance;
    
}
