from locust import HttpLocust, TaskSet, task

class GetGoodsById(TaskSet):
    @task(1)
    def getGoodsById(self):
        self.client.get("/user/goods/101")

class GetAllGoods(TaskSet):
    @task(1)
    def getAllGoods(self):
        self.client.get("/user/goods?action=close")

class GetAllGoodsTimeout(TaskSet):
    @task(1)
    def GetAllGoodsTimeout(self):
        self.client.get("/user/goods")

class UserBehavior(TaskSet):
    tasks = {
        #GetGoodsById: 1,
        #GetAllGoods: 1,
        GetAllGoodsTimeout: 1,
    }

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 2000
    max_wait = 5000
