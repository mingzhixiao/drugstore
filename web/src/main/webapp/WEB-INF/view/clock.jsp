<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>clock</title>
    <link rel="stylesheet" href="/static/clock/style.css">

</head>
<body>

<div>
    <div class="calendar">
        <div class="header">
            <a data-action="prev-month" href="javascript:void(0)" title="Previous Month"><i></i></a>
            <div class="text" data-render="month-year"></div>
            <a data-action="next-month" href="javascript:void(0)" title="Next Month"><i></i></a>
        </div>
        <div class="months" data-flow="left">
            <div class="month month-a">
                <div class="render render-a"></div>
            </div>
            <div class="month month-b">
                <div class="render render-b"></div>
            </div>
        </div>
    </div>

</div>


<script  src="/static/clock/index.js"></script>



</body>
</html>