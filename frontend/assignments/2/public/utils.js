/**
 * This javascript file handles all the toggling and events in the mobile section and the desktop section of the page
 */

document.getElementById("plus-btn").addEventListener('click',()=>{
    
    app.style.display = 'block';
    mainMobileContainer.style.display = 'none';
    chat.style.display = 'none';
    mobileNaviSection.style.display = 'none';
    mobileMainIcons.style.display = 'none';
    mobileFeedIcons.style.display = 'none';
    mobilePosts.style.display = 'none';
    mobileAddPosts.style.display = 'block';
    plusBtn.style.display = 'none';
    

    document.querySelector(".reverse").addEventListener('click',()=>{
        app.style.display = 'block';
        mainMobileContainer.style.display = 'none';
        chat.style.display = 'none';
        mobileNaviSection.style.display = 'none';
        mobileMainIcons.style.display = 'flex';
        mobileFeedIcons.style.display = 'flex';
        mobilePosts.style.display = 'block';
        mobileAddPosts.style.display = 'none';
        plusBtn.style.display = 'block';
        
    })
    
})


document.getElementById('profile-photo').addEventListener('click', function() {
    showNavigation();
});
document.getElementById('#go-back').addEventListener('click', function() {
    hideNavigation();
});
function showNavigation() {
    let navigationSection = document.querySelector('.mobile-navigation-section');
    navigationSection.style.display = 'block';
    document.querySelector(".main-icons").style.display = "none";
    document.querySelector(".feed-icons").style.display = "none"
    document.querySelector(".posts").style.display = "none"
}
function hideNavigation() {
    let navigationSection = document.querySelector('.mobile-navigation-section');
    navigationSection.style.display = 'none';
    document.querySelector(".main-icons").style.display = "flex";
    document.querySelector(".feed-icons").style.display = "flex";
    document.querySelector(".posts").style.display = "block";
}
