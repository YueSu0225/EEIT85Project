document.getElementById("contactForm").addEventListener("submit", function(event) {
      event.preventDefault();  // 阻止表单默认提交

      // 获取表单输入框的值
      var name = document.getElementById("name").value;
      var email = document.getElementById("email").value;
      var subject = document.getElementById("subject").value;
      var message = document.getElementById("message").value;

      // 判断姓名是否为空
      if (name === "") {
          Swal.fire({
              icon: 'error',
              title: '提交失敗',
              text: '姓名不能為空',
              timer: 10000,
              showConfirmButton: true
          });
          return;
      }

      // 判断邮箱格式是否正确
      var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      if (!emailPattern.test(email)) {
          Swal.fire({
              icon: 'error',
              title: '提交失敗',
              text: '請輸入有效信箱',
              timer: 10000,
              showConfirmButton: true
          });
          return;
      }

      // 判断主题是否为空
      if (subject === "") {
          Swal.fire({
              icon: 'error',
              title: '提交失敗',
              text: '標題不能為空',
              timer: 10000,
              showConfirmButton: true
          });
          return;
      }

      // 判断消息内容是否少于10字
      if (message.length < 10) {
          Swal.fire({
              icon: 'error',
              title: '提交失敗',
              text: '內容至少10個字',
              timer: 10000,
              showConfirmButton: true
          });
          return;
      }

      // 如果所有输入都合法，显示提交成功提示
      Swal.fire({
          icon: 'success',
          title: '提交成功',
          text: '謝謝您的建議，我們將5-7工作日回復給您，請查收信箱!',
          timer: 10000,
          showConfirmButton: true
      }).then(() => {
          // 清空所有输入框
          document.getElementById("name").value = "";
          document.getElementById("email").value = "";
          document.getElementById("subject").value = "";
          document.getElementById("message").value = "";
      });
  });