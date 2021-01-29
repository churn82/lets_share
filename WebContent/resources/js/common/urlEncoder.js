let urlEncodedForm = data =>{
	/*
		data : urlEncoded 형태로 포매팅하고 싶은 데이터
		속성명 : parameter name
		값 : parameter value
	*/
	let arr = [];
	//for-in : data 객체에 담긴 속성명을 탐색해서 각각 반환하는 반복문
	for(key in data){
		let param = encodeURIComponent(key) + '=' + encodeURIComponent(data[key]);
		arr.push(param);
	}
	return arr.join('&');
}