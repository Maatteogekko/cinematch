const image = document.querySelector('img')

image.addEventListener('click', () => {
    const src = image.getAttribute('src');

    if (src == 'assets/images/splash.png') {
        image.setAttribute('src', 'assets/images/splash-invert.png')
    } else {
        image.setAttribute('src', 'assets/images/splash.png')
    }
})

const changeUserButton = document.querySelector('button')
const heading = document.querySelector('h1')

function setUsername() {
    const userName = prompt('Enter your name')
    if (!userName) {
        return;
    }
    localStorage.setItem('username', userName)
    heading.textContent = `Welcome to Cinematch, ${userName}`
}

const userName = localStorage.getItem('username')
if (userName) {
    heading.textContent = `Welcome to Cinematch, ${userName}`
}
changeUserButton.addEventListener('click', () => {
    setUsername()
})