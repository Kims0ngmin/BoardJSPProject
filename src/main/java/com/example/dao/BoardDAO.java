package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.bean.BoardVO;
import com.example.util.JDBCUtil;


public class BoardDAO {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    private final String BOARD_INSERT = "insert into BOARD (photo, category, title, writer, content) values (?,?,?,?,?)";
    private final String BOARD_UPDATE = "update BOARD set photo=?, category=?, title=?, writer=?, content=? where seq=?";
    private final String BOARD_DELETE = "delete from BOARD where seq=?";
    private final String BOARD_GET = "select * from BOARD  where seq=?";
    private final String BOARD_LIST = "select * from BOARD order by seq desc";

    public int insertBoard(BoardVO vo) {
        System.out.println("===> JDBC로 insertBoard() 기능 처리");
        int result = 0;
        conn = JDBCUtil.getConnection();
        try {
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getPhoto());
            stmt.setString(2, vo.getCategory());
            stmt.setString(3, vo.getTitle());
            stmt.setString(4, vo.getWriter());
            stmt.setString(5, vo.getContent());
            stmt.executeUpdate();
            result = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 글 삭제
    public void deleteBoard(BoardVO vo) {
        System.out.println("===> JDBC로 deleteBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int updateBoard(BoardVO vo) {
        System.out.println("===> JDBC로 updateBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getPhoto());
            stmt.setString(2, vo.getCategory());
            stmt.setString(3, vo.getTitle());
            stmt.setString(4, vo.getWriter());
            stmt.setString(5, vo.getContent());
            stmt.setInt(6, vo.getSeq());


            System.out.println(vo.getPhoto() + "-" + vo.getCategory() + "-" + vo.getTitle() + "-" + vo.getWriter() + "-" + vo.getContent() + "-" + vo.getSeq());
            stmt.executeUpdate();
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public BoardVO getBoard(int seq) {
        BoardVO one = null;
        conn = JDBCUtil.getConnection();
        System.out.println("===> JDBC로 getBoard() 기능 처리");
        try {
            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1, seq);
            rs = stmt.executeQuery();
            if(rs.next()) {
                one = new BoardVO();
                one.setSeq(rs.getInt("seq"));
                one.setCategory(rs.getString("photo"));
                one.setCategory(rs.getString("category"));
                one.setTitle(rs.getString("title"));
                one.setWriter(rs.getString("writer"));
                one.setContent(rs.getString("content"));
                one.setCnt(rs.getInt("cnt"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return one;
    }

    public ArrayList<BoardVO> getBoardList(){
        ArrayList<BoardVO> list = null;
        conn = JDBCUtil.getConnection();
        System.out.println("===> JDBC로 getBoardList() 기능 처리");
        try {
            stmt = conn.prepareStatement(BOARD_LIST);
            rs = stmt.executeQuery();
            list = new ArrayList<BoardVO>();
            BoardVO one = new BoardVO();
            while(rs.next()) {
                one = new BoardVO();
                one.setSeq(rs.getInt("seq"));
                one.setPhoto(rs.getString("photo"));
                one.setCategory(rs.getString("category"));
                one.setTitle(rs.getString("title"));
                one.setWriter(rs.getString("writer"));
                one.setContent(rs.getString("content"));
                one.setRegdate(rs.getDate("regdate"));
                one.setCnt(rs.getInt("cnt"));
                list.add(one);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getPhotoFilename(int seq) {
        String filename = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_LIST);
            stmt.setInt(1, seq);
            rs = stmt.executeQuery();
            if(rs.next()){
                filename = rs.getString("photo");
            }
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("===> JDBC로 getPhotoFilename() 기능 처리");
        return filename;
    }
}