import "./NotFound.css"

function NotFound(){
    return(
        <>
        <div className="container not-found-container" >
        <h3>We can't bear the bad news — the page you're looking for does not exist:(
        </h3>
        <div className="not-found-img-container">
            <img src="https://cdn.discordapp.com/attachments/1167573430388924557/1238008186703974462/Screenshot_2024-05-08_at_11.02.14_PM.png?ex=663db878&is=663c66f8&hm=b647f405693e66e0351a2112013bb7af7e8ed6a9b4d129ca16788dc9f143b9a2&" style={{ width: "60%", height: "auto" }}/>
        </div>
        </div>
        </>
    )
}
export default NotFound;
