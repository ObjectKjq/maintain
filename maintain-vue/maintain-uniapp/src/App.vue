<script>
import {login} from './api'
	export default {
		onLaunch: function() {
			// 全局只调用一次
			login();
		},
		onShow: function() {
			// 小程序从后台进入前台调用
		},
		onHide: function() {
			// 小程序从前台进入后台调用
		}
	}
</script>

<style>
	/*每个页面公共css */
</style>
