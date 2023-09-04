package learn.daniel.rickAndMortyNew.network.response

 class GetEpisodeByIdResponse (
    val airDate:String="",
    val characters:List<String> = listOf(),
    val created:String="",
    val episode:String="",
    val id:Int = 0,
    val name:String = "",
    val url:String = ""
) {

     data class Info(
         val count: Int ,
         val next: String ,
         val pages: Int ,
         val prev: Any
     )
 }