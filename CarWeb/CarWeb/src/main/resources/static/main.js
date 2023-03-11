let search = document.querySelector('.search-box');

document.querySelector('.search-box').onclick = () => {
    search.classList.toggle('active');
}

let header = document.querySelector('header');
window.addEventListener('scroll' , () => {
    header.classList.toggle('shadow', window.scrollY > 0);
});