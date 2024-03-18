let index = {
    init: function () {
        $("#btn-save").on("click", () => { // function 을 사용하는 이유 : this 를 바인딩 하기 위해
            this.save();
        });
        $("#btn-login").on("click", () => { // function 을 사용하는 이유 : this 를 바인딩 하기 위해
            this.login();
        });
    },

    save: function () {
        // alert('user 의 save 함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        // console.log(data)
        // ajax 호출시 default 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 JSON 으로 변경하여 insert 요청
        // ajax 가 통신을 성공하고 json 을 리턴해주면 서버가 자동으로 자바 오브젝트로 변환
        $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), // http 바디 데이터
            contentType: "application/json; charset=utf-8", // 바디 데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 String (생긴게 JSON 이면 javascript object 로 변경)
        }).done(function (resp) {
            alert("회원가입이 완료되었습니다.");
            location.href = "/blog";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },
    login: function () {
        // alert('user 의 save 함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
        };


        $.ajax({
            type: "POST",
            url: "/blog/api/user/login",
            data: JSON.stringify(data), // http 바디 데이터
            contentType: "application/json; charset=utf-8", // 바디 데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 String (생긴게 JSON 이면 javascript object 로 변경)
        }).done(function (resp) {
            alert("로그인이 완료되었습니다.");
            location.href = "/blog";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
}
index.init()