window.addEventListener('resize', updatedata, false);
window.addEventListener('load', init, false);

function init(){
    document.getElementsByClassName("show_viewport")[0].addEventListener("click", clipboard_vs, true);
    updatedata();
}

function updatedata(){
    document.getElementsByClassName("show_width")[0].textContent = window.innerWidth;
    document.getElementsByClassName("show_height")[0].textContent = window.innerHeight;
    document.getElementsByClassName("screen_width")[0].textContent = "screen width: " + screen.width;
    document.getElementsByClassName("screen_height")[0].textContent = "screen height: " + screen.height;
    document.getElementsByClassName("available_width")[0].textContent = "available width: " + screen.availWidth;
    document.getElementsByClassName("available_height")[0].textContent = "available height: " + screen.availHeight;
    document.getElementsByClassName("browser_width")[0].textContent = "browser width: " + window.outerWidth;
    document.getElementsByClassName("browser_height")[0].textContent = "browser height: " + window.outerHeight;
    document.getElementsByClassName("viewport_width")[0].textContent = "viewport width: " + window.innerWidth;
    document.getElementsByClassName("viewport_height")[0].textContent = "viewport height: " + window.innerHeight;
    document.getElementsByClassName("pixel_ratio")[0].textContent = "Device pixel ratio: " + window.devicePixelRatio;
    document.getElementsByClassName("user_agent")[0].textContent = "User agent: " + navigator.userAgent;
}

function clipboard_vs(){
    setClipboard(window.innerWidth + " x " + window.innerHeight);
    document.getElementsByClassName("copyinfo")[0].textContent = "Copied!";
    setTimeout(copyinforestore, 1000);
}

function copyinforestore(){
    document.getElementsByClassName("copyinfo")[0].textContent = "Click to copy to clipboard";
}

function setClipboard(value) {
    var tempInput = document.createElement("input");
    tempInput.style = "position: absolute; left: -1000px; top: -1000px";
    tempInput.value = value;
    document.body.appendChild(tempInput);
    tempInput.select();
    document.execCommand("copy");
    document.body.removeChild(tempInput);
}
