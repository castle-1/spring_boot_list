package edu.bit.ex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.bit.ex.mapper.BoardMapper;
import edu.bit.ex.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardTests {

   @Autowired
   private BoardMapper mapper;

   @Autowired
   private DataSource ds;

   @Test
   public void testDataSource() {
      System.out.println("DS=" + ds);

      try (Connection conn = ds.getConnection()) {
         System.out.println("Cooooooooonn=" + conn);

         assertThat(conn).isInstanceOf(Connection.class);
         assertEquals(100, getLong(conn, "select 100 from dual"));   //부하테스트
         // assertTrue(0 < getLong(conn, "select count(*) from User"));

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   private long getLong(Connection conn, String sql) {
      long result = 0;
      try (Statement stmt = conn.createStatement()) {
         ResultSet rs = stmt.executeQuery(sql);
         if (rs.next()) {
            result = rs.getLong(1);
         }
         rs.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return result;
   }

   @Test
   public void testGetList() {

      System.out.println(mapper);
      System.out.println(mapper.getlist().size());

      for (BoardVO vo : mapper.getlist()) {
         log.info(vo.getnName());
         System.out.println(vo.getnName());
      }

   }

}