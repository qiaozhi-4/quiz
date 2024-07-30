/** 格式化时间 */
export function formatDate(date : Date, format = 'YYYY-MM-DD HH:mm:ss') {
	// 获取年、月、日、小时、分钟、秒
	const year = date.getFullYear();  // 获取年份
	const month = String(date.getMonth() + 1).padStart(2, '0'); // 获取月份，补全两位数
	const day = String(date.getDate()).padStart(2, '0'); // 获取日期，补全两位数
	const hours = String(date.getHours()).padStart(2, '0'); // 获取小时，补全两位数
	const minutes = String(date.getMinutes()).padStart(2, '0'); // 获取分钟，补全两位数
	const seconds = String(date.getSeconds()).padStart(2, '0'); // 获取秒，补全两位数

	// 使用正则表达式替换格式化字符串中的占位符
	const formattedDate = format
		.replace('YYYY', year)
		.replace('MM', month)
		.replace('DD', day)
		.replace('HH', hours)
		.replace('mm', minutes)
		.replace('ss', seconds);
	return formattedDate; // 返回格式化后的字符串
}