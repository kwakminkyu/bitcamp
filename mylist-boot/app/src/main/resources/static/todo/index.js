var titleInput = $("#title-input")
titleInput.css("display", "none")

var tbody = $("#todo-tbl")

$("#todo-input").keyup(function(e) {
  if (e.keyCode == 27) {
    $(e.target).val("")
  } else if (e.keyCode == 13) {
    if ($(e.target).val() == "") {
    Swal.fire("필수 입력 항목이 비었습니다.")
    return
    }
    fetch(`/todo/add?title=${e.target.value}`)
    .then(function(response){
      return response.text()
    })
    .then(function(text){
      console.log(text)
      location.reload()
    })
  }
})

// 삭제 버튼은 동적 태그 이므로 부모 태그에 이벤트를 준다.
// tbody.onclick = function(e) {
//   if (e.target instanceof HTMLButtonElement) {
//     var no = e.target.getAttribute("data-no")
//     fetch(`/todo/delete?index=${no}`)
//     .then(function(response){
//       return response.json()
//     })
//     .then(function(result){
//       if (result == 0) {
//         window.alert("삭제 실패")
//       } else {
//         location.reload()
//       }
//     })
//   }
// }

fetch("/todo/list")
.then(function(response){
  return response.json();
})
.then(function(todoList){
  console.log(todoList)
  for (todo of todoList) {
  
  var checkedOption = ""
  var titleTdOption = ""
  if (todo.done) {
    checkedOption = "checked"
    titleTdOption = "class='todo-checked'"
  }
  $("<tr>").attr("data-no", todo.no).html(
                  `<td><input type="checkbox" ${checkedOption} onchange="checkTodo(${todo.no}, event.target.checked)"></td>
                  <td class="todo-title"><span ${titleTdOption}>${todo.title}</span></td>
                  <td><button type="button" class="btn btn-primary btn-sm" onclick="updateTodo(${todo.no})">변경</button></td>
                  <td><button type="button" class="btn btn-primary btn-sm" onclick="deleteTodo(${todo.no})">삭제</button></td>`
                  ).appendTo(tbody)
 }
 $("#todo-input").focus()
})

function updateTodo(no) {
  var titleTd = $(`tr[data-no="${no}"] > td.todo-title`)
  var titleSpan = titleTd.find("span")

  if (titleInput.parent()[0] instanceof HTMLTableCellElement) {
    titleInput.parent().find("span").css("display", "")
  }
  titleSpan.css("display", "none")
  titleInput.val(titleSpan.html()) 
  titleTd.append(titleInput)
  titleInput.attr("data-no", no)
  titleInput.css("display", "")
}


function deleteTodo(no) {
  fetch(`/todo/delete?no=${no}`)
  .then(function(response) {
    return response.json()
  })
  .then(function(result) {
    if (result == 0) {
        window.alert("삭제 실패")
      } else {
        location.reload()
    }
  })
}

function checkTodo(no, checked) {
  fetch(`/todo/check?no=${no}&done=${checked}`)
  .then(function(response){
    return response.json()
    .then(function(result){
      if (result == 0) {
        window.alert("변경 실패")
      } else {
        var titleSpan = $(`tr[data-no="${no}"] > td.todo-title > span`)
        if (checked) {
          titleSpan.addClass("todo-checked")
        } else {
          titleSpan.removeClass("todo-checked")
        }
      }
    })
  })
}

titleInput.keyup(function(e) {
  var no = titleInput.attr("data-no")
  var titleSpan = titleInput.parent().find("span")
  var originTitle = titleSpan.html()

  if (e.keyCode == 27) {
    cancelTodoEditing()
  } else if (e.keyCode == 13 && titleInput.value != "" && originTitle != titleInput.value) {
    fetch(`/todo/update?no=${no}&title=${titleInput.val()}`)
    .then(function(response){
      return response.json()
    })
    .then(function(result){
      if (result == 0) {
        window.alert("변경 실패")
      } else {
        console.log("변경 완료")
        titleSpan.html(titleInput.val())
        cancelTodoEditing()
      }
    })
  }
})

function cancelTodoEditing() {
  titleInput.parent().find("span").css("display", "")
  titleInput.css("display", "none")
  $(document.body).append(titleInput)
}