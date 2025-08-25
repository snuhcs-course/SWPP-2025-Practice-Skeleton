
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse, HttpResponseNotAllowed
from .models import Hero
import json
from rest_framework.decorators import api_view

@api_view(['POST'])
@csrf_exempt
def create_hero(request):
    
    if request.method == 'POST':
        try:
            body = request.body.decode('utf-8')
            hero_name = json.loads(body)['name']
            hero_age = json.loads(body)['age']
            hero_score = json.loads(body)['score']
        except (KeyError, json.JSONDecodeError):
            return JsonResponse({'error': 'Invalid request'}, status=400)
        
        hero = Hero(name=hero_name, age=int(hero_age), score=int(hero_score))
        hero.save()
        response_dict = {'id': hero.id, 'name': hero.name, 'age': hero.age, 'score': hero.score}
        return JsonResponse(response_dict, status=201) # {"ok": True} also okay
    
    else:
        return HttpResponseNotAllowed(['POST'])

@api_view(['GET'])
@csrf_exempt
def hero_count(request):
    if request.method == 'GET':
        return JsonResponse({"count": Hero.objects.count()})
    else:
        return HttpResponseNotAllowed(['GET'])



# @csrf_exempt
# def hero_list(request):

#     if request.method == 'GET':
#         heroes = Hero.objects.all()
#         hero_list = []
        
#         for hero in heroes:
#             hero_list.append({
#                 'id': hero.id,
#                 'name': hero.name,
#                 'age': hero.age,
#             })
#         return JsonResponse(hero_list, safe=False)

#     elif request.method == 'POST':
#         try:
#             body = request.body.decode('utf-8')
#             hero_name = json.loads(body)['name']
#         except (KeyError, json.JSONDecodeError) as e:
#             return JsonResponse({'error': 'Invalid request'}, status=400)
        
#         hero = Hero(name=hero_name)
#         hero.save()
#         response_dict = {'id': hero.id, 'name': hero.name}
#         return JsonResponse(response_dict, status=201)

#     else:
#         return HttpResponseNotAllowed(['GET', 'POST'])
    
# @csrf_exempt
# def hero_info(request, hero_id):
    
#     if request.method == 'GET':
#         try:    
#             hero = Hero.objects.get(id=hero_id)
#             return JsonResponse({'id': hero.id, 'name': hero.name, 'age': hero.age})
#         except Hero.DoesNotExist:
#             return JsonResponse({'error': 'Hero not found'}, status=404)     
    
#     elif request.method == 'PUT':
#         try:    
#             hero = Hero.objects.get(id=hero_id)
#             body = request.body.decode('utf-8')
#             hero_name = json.loads(body)['name']
#             hero_age = json.loads(body)['age']
#             hero.name = hero_name
#             hero.age = hero_age
#             hero.save()
#             return JsonResponse({'id': hero.id, 'name': hero.name, 'age': hero.age})
#         except Hero.DoesNotExist:
#             return JsonResponse({'error': 'Hero not found'}, status=404)     
            
#     else:
#         return HttpResponseNotAllowed(['GET', 'PUT'])



