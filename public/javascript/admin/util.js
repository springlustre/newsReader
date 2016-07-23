/** format timestamp to yyyy-MM-dd hh:mm*/
function formatDate(timestamp){
  //console.log("-------------"+now);
  var now=new Date(timestamp);
  var   year=now.getFullYear();
  var   month=now.getMonth()+1;
  var   date=now.getDate();
  var   hour=now.getHours();
  var   minute=now.getMinutes();
  var   second=now.getSeconds();
  return   year+"-"+month+"-"+date+"   "+hour+":"+minute;
}

/**get parametre from get url*/
function GetQueryString(name)
{
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if(r!=null)return  unescape(r[2]); return null;
}