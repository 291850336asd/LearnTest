public class Test {

    public static void main(String[] args) {

        System.out.println(hash(25929,2));
        System.out.println(hash(22291,2));

        System.out.println(hash(25929,32));
        System.out.println(hash(22291,32));

        System.out.println("---------");
        System.out.println(hash("22291",2));
        System.out.println(hash("25929",2));
        System.out.println(hash("22291",32));
        System.out.println(hash("25929",32));
        System.out.println("----------");
        System.out.println(hash("10000004503",2));
        System.out.println(hash("10000000633",2));
        System.out.println(hash("10000000008",2));
    }


    public static int hash(Object o, int shardall){
        int hashCode = o.hashCode();
        int shard = hashCode % shardall;
        return (Math.abs(shard) + 1);
    }

}
