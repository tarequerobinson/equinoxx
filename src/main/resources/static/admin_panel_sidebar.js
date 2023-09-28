window.addEventListener('DOMContentLoaded', function() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        document.getElementById('admin_panel_sidebar').innerHTML = xhr.responseText;
      }
    };
    xhr.open('GET', '/admin_panel_sidebar', true);
    xhr.send();
  });
  