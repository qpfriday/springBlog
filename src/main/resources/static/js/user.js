let index = {
    init: function () {
        $("#btn-save").on("click", () => { // function 을 사용하는 이유 : this 를 바인딩 하기 위해
            this.save();
        });
        $("#btn-update").on("click", () => { // function 을 사용하는 이유 : this 를 바인딩 하기 위해
            this.update();
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
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http 바디 데이터
            contentType: "application/json; charset=utf-8", // 바디 데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 String (생긴게 JSON 이면 javascript object 로 변경)
        }).done(function (resp) {
            if (resp.status === 500) {
                alert("회원가입이 실패하였습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },
    update: function () {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("회원수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
}
index.init()