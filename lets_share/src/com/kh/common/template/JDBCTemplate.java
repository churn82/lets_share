package com.kh.common.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/*   1. JDBCTemplate 생성
   2. JDBCTemplate Singleton 패턴 적용
   3. DataAccessException 생성 (UnChecked Exception)
   4. Transaction관리를 Service 단에서 처리하기 위해 Connection객체를 Service에서 생성하도록 변경
   5. Dao에서 SQLException 이 발생할 경우 DataAccessException을 발생 시키도록 수정
   6. Serivce의 메서드 중에서 transaction처리가 필요한 메서드의 경우 DataAccessException을 예외처리하여 트랜잭션 관리*/

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;


public class JDBCTemplate {
   
   //Singleton 패턴
   //클래스의 인스턴스가 하나만 생성되어야 할 때 사용하는 디자인 패턴
   //첫째, 생성자를 private으로 바꿔서 외부에서 JDBCTemplate의 생성을 차단
   //둘째, 자기 자신의 주소를 보관할 스태틱 변수를 하나 만든다
   private static JDBCTemplate instance;
   //셋째,JDBCTemplate의 인스턴스를 반환받을 수 있는 게터메서드 생성. 외부에서 JDBCTemplate의 인스턴스를 반환받기 위한 메서드
   public static JDBCTemplate getInstance() {
      //넷째, 가장 처음에는 인스턴스가 널일거니까 jdbcTemplate 넣고 그 후에는 그냥 
      if(instance == null) {
         instance = new JDBCTemplate();
      }
      return instance;
   }
   
   private JDBCTemplate() {
      
   }
   
   public Connection getConnection() {
      String url = "jdbc:oracle:thin:@letsshare_high?TNS_ADMIN=C:/CODE/E_SERVLET/Wallet_letsshare";
      String user = "admin";
      String password = "Jiyoung102938";
      
      Properties info = new Properties();     
       info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, user);
       info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, password);          
       info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");    
     
      Connection conn = null;
      try {
         OracleDataSource ods = new OracleDataSource();
          ods.setURL(url);    
          ods.setConnectionProperties(info);
         conn = ods.getConnection();
         //Transaction 관리를 위해 autoCommit끄기
         conn.setAutoCommit(false);
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return conn;
   }
   
   //commit 메서드
   public void commit(Connection conn) {
      try {
         conn.commit();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   //rollback메서드
   public void rollback(Connection conn) {
      try {
         conn.rollback();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
      public void close(ResultSet rset) {
         try {
            if(rset != null && !rset.isClosed()) {
               rset.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      public void close(Statement stmt) {
         try {
            if(stmt != null && !stmt.isClosed()) {
               stmt.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      public void close(Connection conn) {
         try {
            if(conn != null && !conn.isClosed()) {
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   
      public void close(ResultSet rset, Statement stmt, Connection conn) {
         close(rset);
         close(stmt);
         close(conn);
      }
      
      public void close(ResultSet rset, Statement stmt) {
         close(rset);
         close(stmt);
      }
      
      public void close(Statement stmt, Connection conn) {
         close(stmt);
         close(conn);
      }
   
}