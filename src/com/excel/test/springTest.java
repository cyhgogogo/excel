package com.excel.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.excel.pojo.T0Register;
import com.excel.pojo.T3_T4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring*.xml"})
@WebAppConfiguration
public class springTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getAccount() throws Exception {
        try {
            MockHttpSession session = new MockHttpSession();//session
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/login")
                            .session(session)
                            .contentType(MediaType.APPLICATION_JSON)
//                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                            .param("username", "1515")
                            .param("password", "1515")
            )
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();

            int status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            String result = mvcResult.getResponse().getContentAsString();
            System.out.println("接口返回结果：" + result);


            JSONObject resultObj = JSON.parseObject(result);
            JSONObject json = new JSONObject();
            T0Register t0Register = new T0Register();
            T3_T4 t3_t4 = new T3_T4();
            String[][] register = new String[15][8];
            //市场信息
            String[][] market = new String[8][5];
            //存款业务信息登记
            String[][][] repositService = new String[8][4][6];
            //贷款业务信息登记表
            String[][][] loadService = new String[8][4][12];
            for (int i = 0; i < register.length; i++) {
                for (int s = 0; s < register[i].length; s++)
                    register[i][s] = "";
            }
            for (int i = 0; i < market.length; i++) {
                for (int s = 0; s < market[i].length; s++)
                    market[i][s] = "";
            }
            for (int x = 0; x < repositService.length; x++) {
                for (int i = 0; i < repositService[x].length; i++) {
                    for (int s = 0; s < repositService[x][i].length; s++)
                        repositService[x][i][s] = "";
                }
            }
            for (int x = 0; x < loadService.length; x++) {
                for (int i = 0; i < loadService[x].length; i++) {
                    for (int s = 0; s < loadService[x][i].length; s++)
                        loadService[x][i][s] = "";
                }
            }
            register[0][0] = "8000";
            register[2][0] = "购买";
            register[6][0] = "√";
            register[7][0] = "√";
            register[8][0] = "250";
            register[9][0] = "120";
            register[10][0] = "130";
            register[11][0] = "140";
            register[12][0] = "150";
            loadService[0][0][0] = "6000";
            loadService[0][0][1] = "2";


            loadService[0][0][5] = "6000";
            loadService[0][0][6] = "浮动";
            loadService[2][0][0] = "6000";
            loadService[2][0][1] = "2";
            loadService[2][0][2] = "0.05";
            loadService[2][0][3] = "信用";
            loadService[2][0][4] = "AA";
            loadService[2][0][5] = "6000";
            loadService[7][0][6] = "浮动";
            market[0][0] = "0.01";
            market[1][1] = "0.04";
            market[2][2] = "0.04";
            market[3][3] = "B及B以下";
            market[4][4] = "C及C以下";
            repositService[0][0][0] = "8980";
            repositService[0][0][1] = "1";
            repositService[1][0][2] = "0.05";
            repositService[2][0][3] = "柜台";
            repositService[7][0][4] = "固定";
            t0Register.setRegister(register);
            t0Register.setLoadService(loadService);
            t0Register.setMarket(market);
            t0Register.setRepositService(repositService);
            String[][] nationalDebtPurchase = new String[8][12];
            for (int x = 0; x < nationalDebtPurchase.length; x++) {
                for (int i = 0; i < nationalDebtPurchase[x].length; i++) {
                    nationalDebtPurchase[x][i] = "";
                }
            }
            //T3:国债交易记录表
            String[][] nationalDebtTrade = new String[8][14];
            for (int x = 0; x < nationalDebtTrade.length; x++) {
                for (int i = 0; i < nationalDebtTrade[x].length; i++) {
                    nationalDebtTrade[x][i] = "";
                }
            }
            //T4：投融资收益表
            String[][] investProfit = new String[8][4];
            for (int x = 0; x < investProfit.length; x++) {
                for (int i = 0; i < investProfit[x].length; i++) {
                    investProfit[x][i] = "";
                }
            }
            //T5:同业拆入
            String[][] interBank = new String[8][4];
            for (int x = 0; x < interBank.length; x++) {
                for (int i = 0; i < interBank[x].length; i++) {
                    interBank[x][i] = "";
                }
            }
            //T5:同业拆出
            String[][] outBank = new String[12][7];
            for (int x = 0; x < outBank.length; x++) {
                for (int i = 0; i < outBank[x].length; i++) {
                    outBank[x][i] = "";
                }
            }

            outBank[0][0] = "1456";
            outBank[0][1] = "2";
            outBank[0][2] = "0.05";

            nationalDebtTrade[0][0] = "45754";
            nationalDebtTrade[0][1] = "2";
            nationalDebtTrade[0][2] = "2";
            nationalDebtTrade[0][3] = "0";
            nationalDebtTrade[0][4] = "123";
            nationalDebtTrade[0][5] = "23";

            nationalDebtPurchase[0][0] = "2345";
            nationalDebtPurchase[4][1] = "1";
            nationalDebtPurchase[5][0] = "5";
            nationalDebtPurchase[6][3] = "2";
            nationalDebtPurchase[7][5] = "5";

            investProfit[0][0] = "123";
            investProfit[3][1] = "1";
            investProfit[4][2] = "432";
            investProfit[5][3] = "65";
            t3_t4.setOutBank(outBank);
            t3_t4.setInterBank(interBank);
            t3_t4.setInvestProfit(investProfit);
            t3_t4.setNationalDebtPurchase(nationalDebtPurchase);
            t3_t4.setNationalDebtTrade(nationalDebtTrade);


            System.out.println("------------------------------------------------------");

//
            json.put("t0", t0Register);
            json.put("t3_t4", t3_t4);
//            json.put("t0", "");
//            json.put("t3_t4", "");
            System.out.println(json.toJSONString());
            // 判断接口返回json中success字段是否为true
            Assert.assertTrue(resultObj.getBooleanValue("success"));

            mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/calculate").characterEncoding("UTF-8")
                            .session(session)
                            .contentType(MediaType.APPLICATION_JSON).content(json.toJSONString())//json参数
            )
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            result = mvcResult.getResponse().getContentAsString();
            System.out.println("接口返回结果：" + result);
            resultObj = JSON.parseObject(result);

            mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/sheethtml")
                            .characterEncoding("UTF-8")
                            .session(session)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                            .param("sheet", "T10损益表")
            )
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            result = mvcResult.getResponse().getContentAsString();
            System.out.println("接口返回结果：" + result);
            resultObj = JSON.parseObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
