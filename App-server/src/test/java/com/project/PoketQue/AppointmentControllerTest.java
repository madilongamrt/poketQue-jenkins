package com.project.PoketQue;


import com.project.PoketQue.Controller.AppointmentController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class  AppointmentControllerTest {

    private MockMvc mock;


    @InjectMocks
    private AppointmentController appoint;

    @Before
    public void setUp_Mvc() throws  Exception{
     mock = MockMvcBuilders.standaloneSetup(appoint).build();
    }

    /**
     * Test add appointment controller resource to send the correct URL
     */

    @Test
    public void que_Resource1_Url() throws Exception{

        AppointmentController app= new AppointmentController();

        //Complete the assertion  of the URL HHTP request using andExpect() method.

        mock.perform(MockMvcRequestBuilders.post("api/appointment/add").
                param("param1", "value1")).
                andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void test_addAppointment_Resource1_Url(){
        try {
            mock.perform(MockMvcRequestBuilders.get("/addAppointments/").param("param1", "value1").
                    param("param1", "value1").
                    param("param1", "value1")).
                    andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.toString();
        }
    }


    @Test
    public void test_addAppointment_Resource2_Url(){
        try {
            mock.perform(MockMvcRequestBuilders.get("/addAppointments/")).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.toString();
        }
    }

    /**
     * Test find Appointment controller resource to send the correct URL
     */
    @Test
    public void test_findAppointment_Resource1_Url(){
       //empty string
        String reference = "";

        try {
            mock.perform(MockMvcRequestBuilders.get("/findAppointments/"+  reference)).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.toString();
        }
    }

    @Test
    public void test_findAppointment_Resource2_Url(){
       //pass the wrong reference
        String reference = "reufw";

        try {
            mock.perform(MockMvcRequestBuilders.get("/findAppointments/"+  reference)).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.toString();
        }
    }



    @Test
    public void test_move_appointment_Resource1_Url(){
        //pass the correct reference
        String reference = "reufw";
        try {

            mock.perform(MockMvcRequestBuilders.get("/appointment/move/"+  reference)).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.toString();
        }
    }

    @Test
    public void test_move_appointment_Resource2_Url(){
        //pass null
        String reference = "";
        try {

            mock.perform(MockMvcRequestBuilders.get("/appointment/move/"+  reference)).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.toString();
        }
    }




    /**
     * Test Branch controller resource to send the correct URL
     */
    @Test
    public void test_Branch_Resource1_Url(){
            int branchID= 1;
        try {

            mock.perform(MockMvcRequestBuilders.get("/add"+  branchID)).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.toString();
        }
    }


    @Test
    public void test_Branch_Resource2_Url(){
        int branchID= 1;
        try {

            mock.perform(MockMvcRequestBuilders.get("api/statistic"+  branchID)).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.toString();
        }
    }



}
