package com.project.PoketQue;


import com.project.PoketQue.Controller.BranchController;
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
public class BranchControllerTest {

   private  MockMvc  mvc;


   @InjectMocks
   private BranchController branch;

   /*
   Set up mvc builder for spring testing framework
    */
   @Before
    public void setUp(){
       mvc = MockMvcBuilders.standaloneSetup(branch).build();
   }


   @Test
   public void test_add_branch_Url_resource(){
      try {
         mvc.perform(MockMvcRequestBuilders.post("api/branch/addBranch")).andExpect(MockMvcResultMatchers.status().isOk());
      }catch (Exception e){
         e.toString();
      }
   }

   @Test
    public void test_get_branch_Url_resource(){
      try {
         mvc.perform(MockMvcRequestBuilders.post("api/branch/getBranch/")).andExpect(MockMvcResultMatchers.status().isOk());
      }catch (Exception e){
         e.toString();
      }
   }

}
