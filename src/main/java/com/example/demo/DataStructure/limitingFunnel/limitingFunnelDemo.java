package com.example.demo.DataStructure.limitingFunnel;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
/**
 * @program: demo
 * @description: 限流漏斗
 * @author: xiuchao Song
 * @create: 2019-11-12 17:02
 **/
@Slf4j
public class limitingFunnelDemo {
    /**
     * 漏斗的数据结构
     *
     */
    class Funnel {

        /**
         * 漏斗容量
         */
        private int capacity;
        /**
         * 漏斗流速
         */
        private float leakingRate;
        /**
         * 漏斗剩余空间
         */
        private int leftQuota;
        /**
         * 漏水时间
         */
        private long leakingTs;

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        /**
         * 腾出空间
         */
        private void makeSpace() {
            long nowTime = System.currentTimeMillis();
            //计算当前距上次漏水的时间
            long deltatime = nowTime - leakingTs;
            // 计算可释放空间
            int deltaQuota = (int) (deltatime * leakingTs);
            if (deltaQuota < 0) { // 间隔时间太长，整数数字过大溢出
                this.leftQuota = capacity;
                this.leakingTs = nowTime;
                return;
            }
            if (deltaQuota < 1) { // 腾出空间太小，最小单位是1
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs = nowTime;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        /**
         * 注水操作
         * @param quota
         * @return
         */
        boolean watering(int quota) {
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }

    }


    private Map<String, Funnel> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.watering(1); // 需要1个quota
    }

    public static void main(String[] args) {
        limitingFunnelDemo limitingFunnelDemo= new limitingFunnelDemo();
       for (int i=0;i<300;i++){
           boolean actionAllowed = limitingFunnelDemo.isActionAllowed("limitId", "limitActionKey", 50, 10);
           if(actionAllowed){
               log.info("打印日志",i);
               System.out.println(i);
           }
       }
    }

}
