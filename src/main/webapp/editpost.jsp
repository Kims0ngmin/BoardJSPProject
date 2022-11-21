<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.example.dao.BoardDAO"%>
<%@ page import="com.example.FileUpload.FileUpload" %>
<%@ page import="com.example.dao.BoardDAO" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.io.File" %>
<%@ page import="java.nio.channels.FileLock" %>
<%@ page import="com.example.bean.BoardVO" %>

<%
	request.setCharacterEncoding("utf-8");
	BoardDAO boardDAO = new BoardDAO();
	FileUpload upload = new FileUpload();
	BoardVO u = upload.uploadPhoto(request);

	int i=boardDAO.updateBoard(u);
	response.sendRedirect("posts.jsp");
%>