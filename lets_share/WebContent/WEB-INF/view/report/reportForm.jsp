<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div class='rep_content'>
<h2 class="rep">�Ű�Խ���</h2>
<div class="desc_board">
<div class="rep_board">
					���� : <input type="text" name="title" required="required"/>
					<!-- multiple : ������ ���� ������ ����ϴ� �Ӽ� -->
					���� : <input type="file" name="files" id="contract_file" multiple/>
</div>
<div class="text">
					<textarea id="rep-content" class="rep-content" name="content" 
					style="width:100%; height:500px;" required="required"></textarea>
		</div>
	</div>
</div>

















<div class="btn_section">
		<button style="background-color:skyblue; color:white">����</button>
</div>
</body>
</html>