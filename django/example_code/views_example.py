
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse, HttpResponseNotAllowed
from .models import Hero
import json


@csrf_exempt
def hero_list(request):

    if request.method == 'GET':
        heroes = Hero.objects.all()
        hero_list = []
        
        for hero in heroes:
            hero_list.append({
                'id': hero.id,
                'name': hero.name,
                'age': hero.age,
            })
        return JsonResponse(hero_list, safe=False)

    elif request.method == 'POST':
        try:
            body = request.body.decode('utf-8')
            hero_name = json.loads(body)['name']
        except (KeyError, json.JSONDecodeError) as e:
            return JsonResponse({'error': 'Invalid request'}, status=400)
        
        hero = Hero(name=hero_name)
        hero.save()
        response_dict = {'id': hero.id, 'name': hero.name}
        return JsonResponse(response_dict, status=201)

    else:
        return HttpResponseNotAllowed(['GET', 'POST'])


