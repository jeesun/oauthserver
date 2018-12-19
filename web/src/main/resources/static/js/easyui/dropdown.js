/**
 *
 * User: simon
 * Date: 2018/11/04
 * Time: 21:40
 **/
/* When the user clicks on the button,
    toggle between hiding and showing the dropdown content */
function myFunction(obj) {
    console.log($(obj).next());
    $(obj).next().toggle("show");
    //document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {

        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}