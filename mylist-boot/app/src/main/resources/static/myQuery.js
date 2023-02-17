function myQurey(selector, parent) {
  if (typeof(selector) == "string") {
    if (selector.startsWith("<")) {
      var e = document.createElement(selector.substring(1, selector.length - 1))
    } else if (parent != null) {
      var e = parent.querySelector(selector)
    } else {
      var e = document.querySelector(selector)
    }
  } else if (selector instanceof HTMLElement) {
    var e = selector
  }

  e.css = function(name, value) {
    if (arguments.length == 1) {
      return e.style[name]
    } else if (arguments.length == 2) {
      e.style[name] = value
      return e
    }
  }

  e.find = function(selector) {
    return myQurey(selector, e)
  }

  e.html = function(content) {
    if (arguments.length == 0){
      return e.innerHTLM
    } else if (arguments.length == 1) {
      e.innerHTLM = content
      return e
    }
  }

  e.val = function(v) {
    if (arguments.length == 0) {
      return e.value
    } else {
     e.value = v
     return e
    }
  }

  e.attr = function(name, value) {
    if (arguments.length == 1) {
      return e.getAttribute(name)
    } else if (arguments.length == 2) {
      e.setAttribute(name,value)
      return e
    }
  }

  e.parent = function() {
    return myQurey(e.parentNode)
  }

  e.appendTo = function(parent) {
    parent.appendChild(e)
  }

  e.addClass = function(name) {
    e.classList.add(name)
    return e
  }

  e.removeClass = function(name) {
    e.classList.remove(name)
    return e
  }

  e.keyup = function(listener) {
    e.addEventListener("keyup", listener)
    return e
  }
  
  return e
}

var $ = myQurey