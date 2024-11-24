
// add active class to side bar links
document.addEventListener('DOMContentLoaded', function () {
    const sidebarLinks = document.querySelectorAll('.sidebar a');

    sidebarLinks.forEach(link => {
        link.addEventListener('click', function () {
            sidebarLinks.forEach(item => item.classList.remove('active'));

            this.classList.add('active');
        });
    });
});

// changes navbar elements when scrolling
document.addEventListener('DOMContentLoaded', function () {
    const navbar = document.querySelector('.navbar');
    const a = document.querySelectorAll('.navbar a');
    const active = document.querySelector('.navbar a.active');

    window.addEventListener('scroll', function () {
        if (window.scrollY > 50) {
            navbar.style.borderBottom = "1px solid #274690";
            a.forEach(item => {
                if (!item.classList.contains('active')) {
                    item.addEventListener('mouseover', function () {
                        this.style.borderTop = "3px solid #1B264F";
                        this.style.borderBottom = "0";
                    });
                    item.addEventListener('mouseout', function () {
                        this.style.background = "#F5F3F5";
                        this.style.borderTop = "0";
                    });
                }
            });
            active.style.borderTop = "3px solid #6495ED";
            active.style.borderBottom = "0";
        } else {
            navbar.style.borderBottom = "0";
            a.forEach(item => {
                if (!item.classList.contains('active')) {
                    item.addEventListener('mouseover', function () {
                        this.style.borderTop = "0";
                        this.style.borderBottom = "3px solid #1B264F";
                    });
                    item.addEventListener('mouseout', function () {
                        this.style.background = "#F5F3F5";
                        this.style.borderBottom = "0";
                    });
                }
            });
            active.style.borderTop = "0";
            active.style.borderBottom = "3px solid #6495ED";
        }
    });
});

// automatic slideshows
function runSlideshow(slideshowId) {
    let slideIndex = 0;
    const slides = document.querySelectorAll(`#${slideshowId} .slide`);

    function showSlides() {
        for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) { slideIndex = 1 }
        slides[slideIndex - 1].style.display = "block";
    }
    showSlides();
    setInterval(showSlides, 2000);
}

document.addEventListener("DOMContentLoaded", function () {
    runSlideshow("ss1");
    runSlideshow("ss2");
    runSlideshow("ss3");
    runSlideshow("ss4");
    runSlideshow("ss5");
    runSlideshow("ss6");
    runSlideshow("ss7");
    runSlideshow("ss8");
    runSlideshow("ss9");
    runSlideshow("ss10");
    runSlideshow("ss11");
    runSlideshow("ss12");
    runSlideshow("ss13");
    runSlideshow("ss14");
});