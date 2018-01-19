package com.tombigun.loadbalance;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tombigun
 * @version 1.0
 * @date 2018/1/19
 */
public class WeightRoundRobin {

    /**上次选择的服务器*/
    private int currentIndex = -1;
    /**当前调度的权值*/
    private int currentWeight = 0;
    /**最大权重*/
    private int maxWeight;
    /**权重的最大公约数*/
    private int gcdWeight;
    /**服务器数*/
    private int serverCount;

    private List<Server> servers = new ArrayList<>();

    private int greaterCommonDivisor(int a, int b){
        BigInteger aBig = new BigInteger(String.valueOf(a));
        BigInteger bBig = new BigInteger(String.valueOf(b));
        return aBig.gcd(bBig).intValue();
    }

    private int greatestCommonDivisor(List<Server> servers){
        if (servers == null || servers.size() == 0)
            return 0;

        int divisor = servers.get(0).getWeight();
        for(int i = 1, len = servers.size(); i < len ; i++){
            divisor = greaterCommonDivisor(divisor, servers.get(i).getWeight());
        }

        return divisor;
    }

    private int greatestWeight(List<Server> servers){
        int weight = 0;
        for(Server server : servers){
            if(weight < server.getWeight()){
                weight = server.getWeight();
            }
        }
        return weight;
    }

    /**
     *  算法流程：
     *  假设有一组服务器 S = {S0, S1, …, Sn-1}
     *  有相应的权重，变量currentIndex表示上次选择的服务器
     *  权值currentWeight初始化为0，currentIndex初始化为-1 ，当第一次的时候返回 权值取最大的那个服务器，
     *  通过权重的不断递减 寻找 适合的服务器返回，直到轮询结束，权值返回为0
     */
    private Server getServer(){
        while(true){
            currentIndex = (currentIndex + 1) % serverCount;
            if(currentIndex == 0){
                currentWeight = currentWeight - gcdWeight;
                if(currentWeight <= 0){
                    currentWeight = maxWeight;
                    if(currentWeight == 0){
                        return null;
                    }
                }
            }
            if(servers.get(currentIndex).getWeight() >= currentWeight){
                return servers.get(currentIndex);
            }
        }
    }

    private void init(){
        servers.add(new Server("192.168.1.101", 1));
        servers.add(new Server("192.168.1.102", 2));
        servers.add(new Server("192.168.1.103", 3));
        servers.add(new Server("192.168.1.104", 4));
        servers.add(new Server("192.168.1.105", 5));

        maxWeight = greatestWeight(servers);
        gcdWeight = greatestCommonDivisor(servers);
        serverCount = servers.size();
    }

    public static void main(String[] args){
        WeightRoundRobin weightRoundRobin = new WeightRoundRobin();
        weightRoundRobin.init();

        for(int i = 0; i < 15; i++){
            Server server = weightRoundRobin.getServer();
            System.out.println("server " + server.getIp() + " weight=" + server.getWeight());
        }

    }

    static class Server{
        private String ip;
        private int weight;

        public Server(String ip, int weight) {
            this.ip = ip;
            this.weight = weight;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}


