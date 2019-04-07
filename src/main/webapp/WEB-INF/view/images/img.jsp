<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商品子图</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <c:forEach items="${imagesList}" var="img">
            <div class="col-xs-6 col-md-3">
                <a href="#" class="thumbnail">
                    <img src="<%=request.getContextPath()%>${images.imagePath}" alt="loading..." >
                </a>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>