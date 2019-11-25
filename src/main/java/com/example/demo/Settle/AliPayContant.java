package com.example.demo.Settle;

/**
 * 支付宝支付
 */
public class AliPayContant {
//
    public static final String ALI_GETAWAY_URL = "https://openapi.alipay.com/gateway.do";
    public static final String ALI_CHARSET = "utf-8";
    public static final String ALI_OBJECT = "json";
    public static final String ALI_SIGN_TYPE_RSA2 = "RSA2";
    public static final String ALI_PRIVITE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC4l7GqF8X81Jezp+iWFk2wz7uE3YwVpRHFuOiViCLvYCA5iBAg31+Ni9+5yqoi23ufz7G0p9ZhYUXBEhAS7KSO6mjFgdYHLgnpp6qb2dgQsQPH6oh3Ubso67uQ+EU2q/9yrpHRlvl1C9/zMZUWlPwW4+MbCgMBnjQjc2DtnDWglM/FmzXRP/pLBi1y5i9iR0q2u4V0nTuyCfgMIfYtdfjUD6a8AVnllIjSxYf9ADrW2XuJaOeKzmCBTD2hxxl1N1P8oKz6bYmSc2W6fgCJ3aSD9qGOuEsDYZLLS1B3BMAOFz4qZRaFIPf2a+RRg3UTN3J4gPN+aykpXdxq+Ey6hjmzAgMBAAECggEAPazpfqRs0XoSqcM7UhJnqODvjYgciwrZMlAC3htbWNGsUqykfudDXN7URJZzXCwrVQ0nGe5OEcFXdhfb6QbKQLoPgZZM9Z8rTrdnqKUTrRgH5F73c66KMzT4OYrKTWcQdbfgHsAr3zC1heMWJjJnPBquQdWJhZvfXczqINnljR46MEbKaCwCrrdkRZmroplysRMFGvJfZ+V5y8+qaurMraWTIT9kppshQmqFlXheFW2tXkv/hrDLlfSMG5IX+H3D8qzfcUNcq3kLA3o15EaxgkVuGG24O+Fa5kliBD+xgfNxdk1wUtqG70VgTOD0qtP7QTNeQnX8B0oGQ6SQF4JKMQKBgQDfS+23rFlMK/EDsVxpYvSmOdP4VTym5XuLvkqEZuZAafplG5FFNwzLJLhhJkYHm8tjFckAfK8v8AH4GJZFoMBFo00A76au0tlQj0yCwLX2G75bcupeKKfF65CQQMKLgw0ULZRJnkr/Lek1OcYjGo4FR8y2HyPkqU8ktVszCSfuiwKBgQDToKH4EjDBtQsk24c118tTi1isF7nHu+jbeKwgUSIq9WQmh2CvTulLBNZDEqgPjUUIzC4vcDrTAa/R3N+Oab13ZmZLad/cztm2LyRtloCYN8LMIxQTZJePRBsg8srsfrrD0m/Bx7FGovp52TZpBFtmL2YpQ/cSlnT/H1+VvAyueQKBgA9dzEgdhi+lvnpAlFl0Q93EkP+/7eKWXEArojkmU6YPJPocQYz/0tOP/VhCKAOSBxcUgraT3gJwg9Is7vpvq4ufR0dPNqtFO0s98Uf2Nw9urVThvZspNRJ7uYET08+B/z5qPJAsYoDZCflttmLoCun2HPJThkbQF/AwwsvqT2MtAoGAZds/eb2Pl/wCRBghCMo3Q5ui7xlwQnDvz4pSFi/WqSR4so6f+2CYqpHGanv1RZ7x5h0X8Z8rwNlj/Tc4pjvAhFJV0+A+bfFlGemwt8ojVKAp8sTDad4VkE/wWrhg8NjCHXQQdA0gBuZ0decU6BC/yPvxSNU8esbNwukfwVrEI8kCgYB9ftirafdRCu37PRam2xxxFegbkgzPcOp+5qHccS6bGtGyK0JGBu5FkDKh2rSvaF6n9UPMEWf4Arf+XhZRrWRApIhRdMKSGZ6OsMKN+q37SG+E/I6WZ/8OaX1xLqSA/n5pbgpPrWs8c/HpfUPPI3BPtBCXjxK5eTF8D3TKI2GZdg==";
    //支付宝公钥
    public static final String ALI_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi2q8+Yk25kqoIXC0iallbzan+GEA3AwjMvX6SNS/jvLimbKM9kaX0Mon7dhbLjNnvQlOddm2E/im8XqtDL0J3VR8c1LXC7heTThvvEnhnUwba6Aqcju8x+nbErrUXjpMkn3MXyrPZH3qVkjipU/0CRxLceK2q/qdB16TCn+kEW6+Hdc1s72FbCxvg4mk3tAR2rLnJAUv2sHx/MjrYntCZqMea52QQc/OBWzowJx2LZd473LYgyaRSxii6lFnXJMEWuCmCouYLT2ksz8y2DzTTi592rYPxQkXrPguy2Ygw60YNiEJCvPogfdEpI1vtWvZQ8qOxlEycfWTI8I3xQIUNQIDAQAB";
    public static final String ALI_APP_ID = "2019102268603032";
    //应用公钥
    public static final String ALI_PUBLIC_KEY_YY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv2DbhwB2wBhF5puroCb6KP3vYwXAhRoOH3B7VncG/jgCetM6vkqfnPqWNAGQxCxeq5JuWMjCRPWwG9BjqvhlnvXFug2esc+63ca86/hlS5dcHe1ATFpudU1wiclvl8+mskHhX0b5e/SKM1AEvX96SWUFNQWL/YR9PMm20JjDOH/cOFhRG4tNLquHD0/YwCdqHt+iiabREQlPryreuVIkhVG3Nn0P0WUQH9VNDs1k4rUeEy87Jltj5zy6gFN04RnRDbUxRUd9sQPPKzK89tKsFrF+OsyvAVHPfsEo0EWPer1AjWbHaWLeTK0/A4IX/ivky4AkXP9ojzYFm4wjUpfe3QIDAQAB";

//    public static final String ALI_GETAWAY_URL = "https://openapi.alipay.com/gateway.do";
//    public static final String ALI_CHARSET = "utf-8";
//    public static final String ALI_OBJECT = "json";
//    public static final String ALI_SIGN_TYPE_RSA2 = "RSA2";
//    public static final String ALI_PRIVITE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC4u3zLgr7f3U7Hc3oUGazrPYmuZ2Ccs7TF9SIBS3+4Fycxk9Xyryaf2J4zoB4y6BaEQ1EL6d46tfgb+EITdcHhPGXsnsVqIx7dPrWWZG6qAHDytwxrKSc5GFhhLsY9G4FPNIt6ZDTO8cCSloctvmc6xPNuwNzc9hWrssQ1F4Hm4xwMI23mGXmAt89yZJdgGNlAP/lw+o+17U1alEvgj1JVIbSbtIE2QhwP5OZGc9GjjF++hPL/YErksWdM4CfOGw+acIzwPQ7jq5u38/8L3CJ+l6bk4tvdDYSDuO8IeaJTq9T3mo0uURbOVOJgjq03N23az7AYyW3zw7tGHfrXG6ojAgMBAAECggEAek1+oMoODu5ZIsl8MGSTTCcwySueFgEQFONnQQ44H6wMgdFLm2GEo91pV+qRM8jeZ3Cd7tJGE/63peTl8jZLRxCStmajctKPs5C57jjN33VtUfQIn8XeNvm8QZMTIg/cu5ChK0W+hKgfg61vD8T4WTtdo5zCXPP7gvJW3oOSuEXR4ix5ySQeBG01wqt9fhrYrDobxwFlzpsKTCQZRr547HFpeQ7LtddmdvAZ5bZSmjS3ylFBEObs93wLepHegNPznZmqbWIaKf9Bg1NlXQikI7w1FJjbd0s0RC14XV8RP9fi+HLlUwJv4JbMXaET5k8fgTjKsCAhnoKx7ndrNploiQKBgQDjn4NWVk+ybUAh6jbiSivVocNn19dVu/yPeHvb73hUO/xufU6gdUMyDSuDZr9bbbSSFMocamrav5+RXs8rcDRhtjR6LslMYY60FtSbJqpmWoYLSShQi2ORTCMqrtW38tsNZK/xHvAFo1kre9Dxl5Lwd7COyRcPjiqClUsLZrTzDQKBgQDPwyL39P/SXZtiXX7i1OSN//m3TJswTNT8+NaK119pmsxxOmR1FzyvvTet2oR2ZWDYZqg3Fs74WLOU4itvgmD5ZFwfXSozKhexJnQzRRm//EzK/8oAjCXR0329EH/8cF+TQW/JzeClV9sEKi7PtI7tDPprjw8jQZmeu3KwS8OF7wKBgQC8P1enN4qBzciUQ9dQHndSHo40e1UjGm0tWj9VDR8+yr1hw1Abc982rZ2m9nqbaodDqN3d0kS81G1rFDXRfuJTTRTpV3gzKxuR5K/AAKI8M/FXtBEbS6v0pjsPObiWgMqYsQSxLRzkDMvaInQS2YA0T0g9TiE8RB70D/QYYcCUoQKBgHYM1LO7XBi143Jy2YQdn3gn+EX0m0FIhsTux0jAs5AXyw6TNT1q5KqegyKfxSBY3jxXqEEVDqkCDZgNIs0LcfqC0e1oTHAh/LBFv0TagJUb0K6PxMGm4F5nI0zoMTDI0SWkytYJyxln4CRvq4WNfKCo/SlccieAQL92luYhRom1AoGAYJS1bPcWbCnW6a1SD9lFc4pAmdnp5QrgMdu4WsJJVkOyJzTzMNyeTo14wFEWvNmCuDVFcS/fpQiaeSWvrx/4xdjFQ7dBQ5wTr/58+CMhkMU8s63HKdKRWtz0Gpz5yo0/uED4+PoSDJrbAkeUyLOo3yO0v7b4aufmSvyJGabilQA=";
//    //支付宝公钥
//    public static final String ALI_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlecpR282BMS/gSI9rpbHmZy5wQRr1voooS5ZztblIICNyEHzZZCPImar8m9m8FE5+esLR2FC/Vdq/ZEkkUbKtB262E7+PQRusf4OJQlmZkptzQYhJDZyVoB6uEjdgo6SKcN5urXSK03jd6aPTchhUu8VD3m9/gPmTD9pwvwC7dVcAQTxhJNl+HefczvnkL2rhuliF7GpkNqWJWYDyWAcTTgUpRkvhUJ5epEqEWhptwgo5hCV8hZ7yDgfL1z5Pv5oD4YABaABLBHHZAPvWxFHaYKqFFCgWhFQ3W6Mx6KNIIxIWKjsJm0PTaJytjtWz6AZJ2hxBxYUy2o2yZoCZvWjiwIDAQAB";
//    public static final String ALI_APP_ID = "2019111869267172";
//    //应用公钥
//    public static final String ALI_PUBLIC_KEY_YY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv2DbhwB2wBhF5puroCb6KP3vYwXAhRoOH3B7VncG/jgCetM6vkqfnPqWNAGQxCxeq5JuWMjCRPWwG9BjqvhlnvXFug2esc+63ca86/hlS5dcHe1ATFpudU1wiclvl8+mskHhX0b5e/SKM1AEvX96SWUFNQWL/YR9PMm20JjDOH/cOFhRG4tNLquHD0/YwCdqHt+iiabREQlPryreuVIkhVG3Nn0P0WUQH9VNDs1k4rUeEy87Jltj5zy6gFN04RnRDbUxRUd9sQPPKzK89tKsFrF+OsyvAVHPfsEo0EWPer1AjWbHaWLeTK0/A4IX/ivky4AkXP9ojzYFm4wjUpfe3QIDAQAB";

}