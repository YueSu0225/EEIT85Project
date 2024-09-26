
//先秀總覽
window.onload = function() {
    showDetails('As-overview');
}; 

function showDetails(blockId) {
	// 隱藏所有 sections
	document.getElementById('As-overview').style.display = 'none';
	document.getElementById('backed_productupdate').style.display = 'none';
	

	// 顯示指定的 section
	document.getElementById(blockId).style.display = 'block';
}
