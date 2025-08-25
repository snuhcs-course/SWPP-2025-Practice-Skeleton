
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
            # TODO: get request and parse json
            pass
        except (KeyError, json.JSONDecodeError):
            return JsonResponse({'error': 'Invalid request'}, status=400)
        
        # TODO: create hero
        pass
    
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
