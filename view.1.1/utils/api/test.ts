import {api} from "../service";

export function getRandomTest(){
	return api.request({url:'/question/get-random-test'})
}